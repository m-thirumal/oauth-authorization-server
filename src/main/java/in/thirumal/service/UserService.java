package in.thirumal.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.thirumal.exception.BadRequestException;
import in.thirumal.exception.ResourceNotFoundException;
import in.thirumal.model.Contact;
import in.thirumal.model.GenericCd;
import in.thirumal.model.Login;
import in.thirumal.model.LoginUser;
import in.thirumal.model.LoginUserName;
import in.thirumal.model.Password;
import in.thirumal.model.UserResource;
import in.thirumal.repository.ContactRepository;
import in.thirumal.repository.GenericCdRepository;
import in.thirumal.repository.LoginUserNameRepository;
import in.thirumal.repository.LoginUserRepository;
import in.thirumal.repository.PasswordRepository;
import jakarta.validation.Valid;

/**
 * @author Thirumal
 *
 */
@Service
public class UserService {
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private GenericCdRepository genericCdRepository;
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private LoginUserRepository loginUserRepository;
	@Autowired
	private LoginUserNameRepository loginUserNameRepository;
	@Autowired
	private PasswordRepository passwordRepository;
	//@Autowired
	//private TokenRepository tokenRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	/**
	 * Create new account for the user
	 * @param userResource
	 * @return 
	 */
	public UserResource createAccount(UserResource userResource) {
		logger.debug("Creating new user.... {}", userResource);
		// Validation
		List<GenericCd> genericCds = genericCdRepository.list("contact", GenericCd.DEFAULT_LOCALE_CD);
		validateEmailAndPhoneNumber(userResource, genericCds);
		// Login user create
		Long loginUserId = loginUserRepository.save(LoginUser.builder().dateOfBirth(userResource.getDateOfBirth()).build());
		LoginUser loginUser = loginUserRepository.findById(loginUserId);
		logger.debug("Created login user id {}", loginUser);
		if (Objects.isNull(loginUser)) {
			throw new ResourceNotFoundException("Not able create an account, Contact support");
		}
		// User Name
		loginUserNameRepository.save(LoginUserName.builder().loginUserId(loginUserId)
				.firstName(userResource.getFirstName()).middleName(userResource.getMiddleName()).lastName(userResource.getLastName()).build());
		// Contact (i.e User ID to login)
		var contacts = new ArrayList<Contact>();
		contacts.add(Contact.builder().contactCd(Contact.EMAIL).loginUserId(loginUserId).loginId(userResource.getEmail()).build());
		contacts.add(Contact.builder().contactCd(Contact.PHONE_NUMBER).loginUserId(loginUserId)
				.loginId(userResource.getPhoneNumber()).build());
		contactRepository.saveAll(contacts);
		// Password
		passwordRepository.save(Password.builder().loginUserId(loginUserId).secretKey(passwordEncoder.encode(userResource.getPassword())).build());
		// Token - 
		//tokenRepository.save(Token.builder().build());
		return get(loginUser.getLoginUuid());
	}

	private void validateEmailAndPhoneNumber(UserResource userResource, List<GenericCd> genericCds) {
		logger.debug("E-mail and phone number validation");
		// E-mail validation
		validateWithRegex(genericCds, Contact.EMAIL, userResource.getEmail(), "The Requested E-Mail is not vaild");
		//Phone Number validation
		validateWithRegex(genericCds, Contact.PHONE_NUMBER, userResource.getPhoneNumber(), "The Requested Phone Number is not vaild");		
	}
	
	private void validateWithRegex(List<GenericCd> genericCds, Long codeCd, String value, String errorMessage) {
		Optional<String> regex = genericCds.stream()
				.filter(g->codeCd.equals(g.getCodeCd())).map(GenericCd::getRegex).findFirst();
		if (regex.isPresent() && !validateRegex(regex.get(), value)) {
			logger.debug("Regex {} is used for {}", regex.get(), value);
			throw new BadRequestException(errorMessage);
		}
	}	
	
	private boolean validateRegex(String regex, String value) {
		return Pattern.compile(regex).matcher(value).matches();
	}

	public UserResource get(UUID loginUuid) {
		logger.debug("Getting the user {}", loginUuid);
		LoginUser loginUser = loginUserRepository.findByUuid(loginUuid);
		if (Objects.isNull(loginUser)) {
			throw new ResourceNotFoundException("The requested user " + loginUuid + " is not available");
		}
		LoginUserName loginUserName = loginUserNameRepository.findByLoginUserId(loginUser.getLoginUserId());
		// Contact
		List<Contact> contacts = contactRepository.findAllByLoginUserId(loginUser.getLoginUserId());
		return buildUserResource(loginUser, loginUserName, contacts);
	}
	

	private UserResource buildUserResource(LoginUser loginUser, LoginUserName loginUserName, List<Contact> contacts) {
		UserResource userResource = new UserResource();
		// Login User
		userResource.setLoginUuid(loginUser.getLoginUuid());
		userResource.setDateOfBirth(loginUser.getDateOfBirth());
		userResource.setAccountCreatedOn(loginUser.getRowCreatedOn());
		// Login User Name
		userResource.setFirstName(loginUserName.getFirstName());
		userResource.setMiddleName(loginUserName.getMiddleName());
		userResource.setLastName(loginUserName.getLastName());
		// Contact
		for (Contact contact : contacts) {
			if (contact.getEndTime().isBefore(OffsetDateTime.now())) {
				continue;
			}
			if (Contact.EMAIL.equals(contact.getContactCd())) {
				userResource.setEmail(contact.getLoginId());
			}
			if (Contact.PHONE_NUMBER.equals(contact.getContactCd())) {
				userResource.setPhoneNumber(contact.getLoginId());
			}
		}
		return userResource;
	}

	public boolean changePassword() {
		return false;
	}

	public Object login(@Valid Login login) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
