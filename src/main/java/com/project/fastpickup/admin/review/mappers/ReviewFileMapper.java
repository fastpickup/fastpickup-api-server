package com.project.fastpickup.admin.review.mappers;

/*
 * Date   : 2023.07.31
 * Author : 이주용
 * E-mail : wndyd0110@gmail.com
 */

import java.util.List;
import java.util.Map;

public interface ReviewFileMapper {

    int registReviewImg(List<Map<String, String>> imageList);

    int deleteReviewImg(Long rno);

}
