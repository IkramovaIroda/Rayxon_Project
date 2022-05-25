package com.company.rayxon.enums;

import com.company.rayxon.entity.Attachment;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "attachment_content")
public class AttachmentContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bytes", nullable = false)
    private byte[] bytes;

    @OneToOne()
    @JoinColumn(name = "attachment_id", nullable = false)
    private Attachment attachment;

}
