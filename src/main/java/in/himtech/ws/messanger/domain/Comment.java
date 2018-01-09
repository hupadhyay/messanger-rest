package in.himtech.ws.messanger.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "COMMENT")
@NamedQueries({ 
	@NamedQuery(name = "allCmntRecords", query = "select c from Comment c where c.message.id = :msgId"),
	@NamedQuery(name = "oneCmntRecords", query = "select c from Comment c where c.id = :cmntId and c.message.id = :msgId") 
})
public class Comment {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "AUTHOR")
	private String author;

	@Temporal(TemporalType.DATE)
	@Column(name = "TIME")
	private Date time;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = Message.class)
	private Message message;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", author=" + author + ", time=" + time + ", message="
				+ message + "]";
	}

}
