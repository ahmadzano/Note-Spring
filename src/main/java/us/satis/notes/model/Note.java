package us.satis.notes.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String uuid;

    private String type;

    private String content;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "created_at")
    private Timestamp cratedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Timestamp getCratedAt() {
        return cratedAt;
    }

    public void setCratedAt(Timestamp cratedAt) {
        this.cratedAt = cratedAt;
    }
}
