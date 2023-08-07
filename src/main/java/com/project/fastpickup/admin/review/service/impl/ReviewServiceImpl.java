package com.project.fastpickup.admin.review.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.fastpickup.admin.qna.dto.QnaListDTO;
import com.project.fastpickup.admin.review.dto.ReviewListDTO;
import com.project.fastpickup.admin.review.dto.ReviewReadDTO;
import com.project.fastpickup.admin.review.dto.ReviewRegistDTO;
import com.project.fastpickup.admin.review.dto.ReviewUpdateDTO;
import com.project.fastpickup.admin.review.mappers.ReviewFileMapper;
import com.project.fastpickup.admin.review.mappers.ReviewMapper;
import com.project.fastpickup.admin.review.service.ReviewService;
import com.project.fastpickup.admin.util.PageRequestDTO;
import com.project.fastpickup.admin.util.PageResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService {

    // 의존성 주입
    private final ReviewMapper reviewMapper;
    private final ReviewFileMapper reviewFileMapper;

    // 맴버 리뷰 리스트
    @Override
    public PageResponseDTO<ReviewListDTO> reviewMemberList(String email, PageRequestDTO pageRequestDTO) {

        List<ReviewListDTO> list = reviewMapper.getReviewMemberList(email, pageRequestDTO);
        int total = reviewMapper.reviewMemberListCount(email, pageRequestDTO);

        return PageResponseDTO.<ReviewListDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    // 리뷰 상세
    @Override
    public ReviewReadDTO reviewRead(Long rno) {

        return reviewMapper.getReviewRead(rno);

    }

    // 가맹점 별 리뷰 리스트
    @Override
    public PageResponseDTO<ReviewListDTO> reviewList(Long rno, PageRequestDTO pageRequestDTO) {

        List<ReviewListDTO> list = reviewMapper.getReviewList(rno, pageRequestDTO);
        int total = reviewMapper.reviewListCount(rno, pageRequestDTO);

        return PageResponseDTO.<ReviewListDTO>withAll()
                .list(list)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

    }

    // 리뷰 등록
    @Override
    public Long registReview(ReviewRegistDTO reviewRegistDTO) {

        log.info("============ Review Create Service ============");

        long gno = reviewRegistDTO.getGno();
        // review 개수 보여 주려고 설계
        long sno = reviewRegistDTO.getSno();


            // 리뷰 등록
            long reviewCnt = reviewMapper.registReview(reviewRegistDTO);

            // selectKey로 가져온 rno값
            Long rno = reviewRegistDTO.getRno();

            // 리뷰 등록 후 gno = rno 처리 하여 리뷰, 답글 구분
            reviewMapper.updateGno(rno);

            List<String> fileNames = reviewRegistDTO.getFileNames();

            if (reviewCnt > 0 && reviewRegistDTO.getFileNames() != null && !reviewRegistDTO.getFileNames().isEmpty()) {

                AtomicInteger index = new AtomicInteger();
                // 등록된 파일 fileNames에서 추출
                List<Map<String, String>> list = fileNames.stream().map(str -> {

                    String uuid = str.substring(0, 36);
                    String fileName = str.substring(37);

                    return Map.of("uuid", uuid, "fileName", fileName, "rno", "" + rno, "ord",
                            "" + index.getAndIncrement());
                }).collect(Collectors.toList());
                log.info(list);

                reviewFileMapper.registReviewImg(list);
            }

            return rno;

    }

    // 리뷰 삭제
    @Override
    public Long removeReview(Long rno) {

        reviewMapper.removeReview(rno);
        reviewFileMapper.deleteReviewImg(rno);

        return rno; 

    }

    // 리뷰 수정
    @Override
    public Long updateReview(ReviewUpdateDTO reviewUpdateDTO) {

        int updateCount = reviewMapper.updateReveiw(reviewUpdateDTO);
        Long rno = reviewUpdateDTO.getRno();

        reviewFileMapper.deleteReviewImg(rno);

        List<String> fileNames = reviewUpdateDTO.getFileNames();

        if (updateCount > 0 && reviewUpdateDTO.getFileNames() != null && !reviewUpdateDTO.getFileNames().isEmpty()) {

            AtomicInteger index = new AtomicInteger();
            // 등록된 파일 fileNames에서 추출
            List<Map<String, String>> list = fileNames.stream().map(str -> {

                String uuid = str.substring(0, 36);
                String fileName = str.substring(37);

                return Map.of("uuid", uuid, "fileName", fileName, "rno", "" + rno, "ord",
                        "" + index.getAndIncrement());
            }).collect(Collectors.toList());
            log.info(list);

            reviewFileMapper.registReviewImg(list);
        }
        return rno;
    }

}
