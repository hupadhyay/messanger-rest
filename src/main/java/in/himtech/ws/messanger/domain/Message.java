package in.himtech.ws.messanger.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author Himanshu Kumar Upadhyay.
 */

/*
 * @XmlRootElement
 * 
 * @JsonIgnoreProperties(ignoreUnknown = true)
 */
@Entity
@Table(name = "MESSAGE", catalog = "test")
@NamedQuery(name = "allRecords", query = "select m from Message m")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "OWNER")
	private String owner;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "MSG_COMMENT", joinColumns = @JoinColumn(name = "MSG_ID"), inverseJoinColumns = @JoinColumn(name = "COMNT_ID"))
	private List<Comment> listComment;

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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@XmlTransient
	public List<Comment> getListComment() {
		return listComment;
	}

	public void setListComment(List<Comment> listComment) {
		this.listComment = listComment;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + ", owner=" + owner + "]";
	}
}
