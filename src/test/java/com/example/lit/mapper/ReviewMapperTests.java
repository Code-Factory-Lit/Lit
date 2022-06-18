package com.example.lit.mapper;

import com.example.lit.domain.vo.SearchDTO;
import com.example.lit.domain.vo.review.ReviewDTO;
import com.example.lit.mapper.review.ReviewMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReviewMapperTests {
    @Autowired
    private ReviewMapper reviewMapper;

    @Test
    public void searchReview(){
        SearchDTO searchDTO = new SearchDTO();

        searchDTO.setCategory("hobby");
        searchDTO.setKeyword("21");
        searchDTO.setType("user_number");

        reviewMapper.searchReview(searchDTO).stream().map(ReviewDTO::toString).forEach(log::info);
    }
}
