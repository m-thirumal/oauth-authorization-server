GenericCd.listByTableName=SELECT * FROM lookup.{TABLE_NAME}_cd AS c LEFT JOIN lookup.{TABLE_NAME}_locale AS l ON l.{TABLE_NAME}_cd = c.{TABLE_NAME}_cd WHERE l.locale_cd = ? AND l.end_time = 'infinity'
#-- Contact
Contact=SELECT * FROM public.contact WHERE
Contact.create=INSERT INTO public.contact(login_user_id, contact_cd, login_id) VALUES (?, ?, ?)
Contact.get=${Contact} contact_id = ?
Contact.getByLoginUserId=${Contact} login_user_id = ? ORDER BY contact_id DESC LIMIT 1
Contact.listByLoginUserId=${Contact} login_user_id = ?
Contact.listByLoginId=${Contact} login_id = ? AND end_time = 'infinity'
Contact.listInLoginId=${Contact} login_id IN (?) AND end_time = 'infinity'
#-- Login User
LoginUser = SELECT * FROM public.login_user WHERE 
LoginUser.create=INSERT INTO public.login_user(date_of_birth) VALUES (?)
LoginUser.get=${LoginUser} login_user_id = ?
LoginUser.getByUuid=${LoginUser} login_uuid = ?
#-- Login User Name
LoginUserName=SELECT * FROM public.login_user_name WHERE 
LoginUserName.create=INSERT INTO public.login_user_name(login_user_id, first_name, middle_name, last_name) VALUES (?, ?, ?, ?)
LoginUserName.get=${LoginUserName} login_user_name_id = ?
LoginUserName.getByLoginUserId=${LoginUserName} login_user_id = ? ORDER BY login_user_name_id DESC LIMIT 1
#-- Password
Password=SELECT * FROM public.password WHERE 
Password.create=INSERT INTO public.password(login_user_id, secret_key) VALUES (?, ?)
Password.get=${Password} password_id = ?
Password.getByLoginUserId=${Password} login_user_id = ?  ORDER BY password_id DESC LIMIT 1
Password.listByLoginUserId=${Password} login_user_id = ? 
#-- Token
Token=SELECT * FROM public.token WHERE
Token.create=INSERT INTO public.token(contact_id, otp, expires_on) VALUES (?, ?, ?)
Token.get=${Token} token_id = ?
Token.getByContactId= ${Token} contact_id = ? ORDER BY token_id DESC LIMIT 1
Token.listByContactId= ${Token} contact_id = ? 