
package mail;


public class EmailSend
{

	private MailSenderInfo	mailInfo	= null;

	/**
	 * 初始化设置发送邮箱（媒介邮箱）。
	 */
	public EmailSend ( )
	{
		// 这个是设置邮件
		mailInfo = new MailSenderInfo ( );
		mailInfo.setMailServerHost ( "smtp.163.com" );
		mailInfo.setMailServerPort ( "25" );// qq port
		mailInfo.setValidate ( true );
		mailInfo.setUserName ( "cug_gis_436_bdg@163.com" );
		mailInfo.setPassword ( "ABC1234567890abc" );// 您的邮箱密码
		mailInfo.setFromAddress ( "cug_gis_436_bdg@163.com" );
	}

	/**
	 * 
	 * 这个函数使用来发送邮件
	 * 
	 * @param to_address
	 * 
	 * @param subject
	 * 
	 * @param content
	 * 
	 */
	public void send ( String to_address, String subject, String content )
	{
		mailInfo.setToAddress ( to_address );
		mailInfo.setSubject ( subject );
		mailInfo.setContent ( content );
		SimpleMailSender sms = new SimpleMailSender ( );
		sms.sendTextMail ( mailInfo );// 发送文体格式
	}

	/**
	 * 这个函数使用来发送邮件
	 * 
	 * @param to_address
	 * 
	 * @param subject
	 * 
	 * @param content
	 * 
	 */
	public void send ( String to_address1, String to_address2, String subject,
			String content )
	{
		mailInfo.setToAddress ( to_address1 );
		mailInfo.setSubject ( subject );
		mailInfo.setContent ( content );
		SimpleMailSender sms = new SimpleMailSender ( );
		sms.sendTextMail ( mailInfo );// 发送文体格式
		mailInfo.setToAddress ( to_address2 );
		sms.sendTextMail ( mailInfo );// 发送文体格式
	}

	/**
	 * 这个函数使用来发送邮件
	 * 
	 * @param to_address
	 * 
	 * @param subject
	 * 
	 * @param content
	 */
	public void send ( String [ ] to_address, String subject, String content )
	{
		SimpleMailSender sms = new SimpleMailSender ( );
		mailInfo.setSubject ( subject );
		mailInfo.setContent ( content );
		for ( int i = 0; i < to_address.length; i++ )
		{
			mailInfo.setToAddress ( to_address[i] );
			sms.sendTextMail ( mailInfo );// 发送文体格式
		}
	}
}
