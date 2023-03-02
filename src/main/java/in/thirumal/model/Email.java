/**
 * 
 */
package in.thirumal.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Thirumal
 *
 */
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@ToString(callSuper = true)
public class Email extends Message implements Serializable {
	
	private static final long serialVersionUID = -7458838274460301305L;
	
	private Set<String> cC;
	private Set<String> bCC;
	//
	private String template;
	//
	private Map<String, Object> model;
	private List<byte[]> attachments;
	private InformationDetail informationDetail;
	
	public static final String AUCTION_CREATE_BY_AUCTIONEER = "auctionCreateByAuctioneer";
	public static final String AUCTION_CREATE_BY_SUPPORT = "auctionCreateBySupport";
	public static final String AUCTION_CANCEL_BY_AUCTIONEER = "auctionCancelByAuctioneer";
	public static final String AUCTION_CANCEL_BY_SUPPORT = "auctionCancelBySupport";
	public static final String DATE_MODIFICATION = "auctionDateModification";
	public static final String AUCTION_END = "auctionEnd";
	public static final String AUCTION_END_REMINDER = "auctionEndReminder";
	public static final String AUCTION_START_REMINDER = "auctionStartReminder";
	public static final String BIDDER_INVITATION = "bidderInvitation";
	public static final String AUCTION_BID_REQUSET_STATUS = "auctionBidRequestStatus";
	public static final String AUCTION_BID_REQUSET_SUBMITTED = "auctionBidRequestSubmitted";
	public static final String AUCTION_BID_ACKNOWLEDGEMENT = "auctionBidAcknowledgement";
	public static final String NEW_BIDS_NOTIFICATION = "auctionBidAcknowledgement";
	public static final String ROLE_ASSIGN = "auctionRoleAssign";
	public static final String ROLE_REVOKE = "auctionRoleRevoke";
	public static final String RESCHEDULE = "auctionReschedule";
	public static final String NEW_BIDS_ALERT = "newBidsNotificationForAuctioneer";
	public static final String AUTHREP_INVITATION = "authRepInvitation";
	public static final String AUCTION_PUBLISH = "auctionPublishNotification";
	public static final String BULK_UPLOAD = "auctionBulkUpload";
	public static final String AUTH_REP_REVOKE = "authRepRevokeNotification";
	
	public Email(Message m) {
		this.messageId = m.getMessageId();
		this.applicationId = m.getApplicationId();
		this.applicationName = m.getApplicationName();
		this.channelCd = m.getChannelCd();
		this.channelLocale = m.getChannelLocale();
		this.messageOf = m.getMessageOf();
		this.sender = m.getSender();
		this.receiver = m.getReceiver();
		this.onBehalf = m.getOnBehalf();
		this.information = m.getInformation();
		this.rowCreatedOn = m.getRowCreatedOn();
		this.messageStatusId = m.getMessageStatusId();
		this.statusCd = m.getStatusCd();
		this.statusLocale = m.getStatusLocale();
		this.statusRowCreatedOn = m.getStatusRowCreatedOn();
		setInformationDetail();
	}
	
	public Email(String sender, Set<String> receiver, String template, Map<String, Object> model, String information,
			Long messageOf) {
		this.sender = sender;
		this.receiver = receiver;
		this.template = template;
		this.model = model;
		this.information = information;
		this.messageOf = messageOf;
	}
	
	public Email(String sender, Set<String> receiver, Set<String> cc, String template, Map<String, Object> model, String information,
			Long messageOf) {
		this.sender = sender;
		this.receiver = receiver;
		this.cC = cc;
		this.template = template;
		this.model = model;
		this.information = information;
		this.messageOf = messageOf;
	}
	
	public void setInformationDetail() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			this.setInformationDetail(objectMapper.readValue(this.information, new TypeReference<InformationDetail>() {}));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}
	
	@Getter@Setter
	@NoArgsConstructor@AllArgsConstructor
	@Builder@ToString
	public
	static class InformationDetail implements Serializable {
		
		private static final long serialVersionUID = 5026163831882298277L;
		
		private String subject;
		private String body;
		
	}
	
}
