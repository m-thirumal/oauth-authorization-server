GenericCd.listByTableName=SELECT * FROM lookup.{TABLE_NAME}_cd AS c LEFT JOIN lookup.{TABLE_NAME}_locale AS l ON l.{TABLE_NAME}_cd = c.{TABLE_NAME}_cd WHERE l.locale_cd = ? AND l.end_time = 'infinity'
#-- Login User
LoginUser = SELECT * FROM public.login_user WHERE 
LoginUser.create=INSERT INTO public.login_user(date_of_birth) VALUES (?)
LoginUser.get=${LoginUser} login_user_id = ?
LoginUser.getByUuid=${LoginUser} login_uuid = ?