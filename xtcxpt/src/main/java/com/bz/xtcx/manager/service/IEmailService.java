package com.bz.xtcx.manager.service;

/**
 * 发送邮件服务
 */
public interface IEmailService {
	
	/**
	* 发送平台注册激活邮件HTML
	 * @param to
	 * @param uuid
	 * @return
	 */
	public boolean sendRegisterEmailHtml(String to, String uuid);
	
	/**
	 * 发送平台注册激活邮件
	 * @param to
	 * @param uuid
	 * @return
	 */
	public boolean sendRegisterEmail(String to, String uuid);
	
	/**
	 * 发送平台验证码邮件
	 * @param to
	 * @param uuid
	 * @return
	 */
	public boolean sendCodeEmail(String to, String uuid);
	
	
    /**
     * 发送简单邮件
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleEmail(String to, String subject, String content);
    
    /**
     * 发送html格式邮件
     * @param to
     * @param subject
     * @param content
     */
    public void sendHtmlEmail(String to, String subject, String content);
    
    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    public void sendAttachmentsEmail(String to, String subject, String content, String filePath);
    
    /**
     * 发送带静态资源的邮件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    public void sendInlineResourceEmail(String to, String subject, String content, String rscPath, String rscId);
}
