package co.fastestpath.api.alerts;

import com.fasterxml.jackson.annotation.JsonProperty;

class AlertEmail {

  private String headers;

  private String dkim;

  @JsonProperty("content-ids")
  private String contentIds;

  private String to;

  private String html;

  private String from;

  @JsonProperty("sender_ip")
  private String senderIp;

  @JsonProperty("spam_report")
  private String spamReport;

  private String envelope;

  private String attachments;

  private String subject;

  @JsonProperty("spam_score")
  private String spamScore;

  @JsonProperty("attachment-info")
  private String attachmentInfo;

  private String charsets;

  @JsonProperty("SPF")
  private String spf;

  public String getHeaders() {
    return headers;
  }

  public String getDkim() {
    return dkim;
  }

  public String getContentIds() {
    return contentIds;
  }

  public String getTo() {
    return to;
  }

  public String getHtml() {
    return html;
  }

  public String getFrom() {
    return from;
  }

  public String getSenderIp() {
    return senderIp;
  }

  public String getSpamReport() {
    return spamReport;
  }

  public String getEnvelope() {
    return envelope;
  }

  public String getAttachments() {
    return attachments;
  }

  public String getSubject() {
    return subject;
  }

  public String getSpamScore() {
    return spamScore;
  }

  public String getAttachmentInfo() {
    return attachmentInfo;
  }

  public String getCharsets() {
    return charsets;
  }

  public String getSpf() {
    return spf;
  }
}
