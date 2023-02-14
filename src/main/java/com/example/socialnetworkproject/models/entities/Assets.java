package com.example.socialnetworkproject.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "assets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Assets {

    @Id
    @Column(name = "asset_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID assetId;

    @Column(name = "asset_url")
    private String assetUrl;

    @Column(name = "asset_size")
    private String assetSize;

    @Column(name = "asset_title")
    private String assetTitle;

    @Column(name = "asset_upload_date")
    private LocalDateTime assetUploadDate;

    @OneToOne(mappedBy = "avatarAsset")
    private Information userAvatar;

    @OneToOne(mappedBy = "backgroundAsset")
    private Information userBackGround;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Posts posts;
}
