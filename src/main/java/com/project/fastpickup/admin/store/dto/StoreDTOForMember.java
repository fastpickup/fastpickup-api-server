package com.project.fastpickup.admin.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTOForMember {
    private Long cno;
    private Long pno;
    private String fileName;
    private String categoryName;
}
