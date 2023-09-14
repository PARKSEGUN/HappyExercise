package com.qkrtprjs.happyexercise.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.qkrtprjs.happyexercise.dto.ItemSaveRequestDto;
import com.qkrtprjs.happyexercise.dto.MemberSaveRequestDto;
import com.qkrtprjs.happyexercise.entitiy.item.Item;
import com.qkrtprjs.happyexercise.entitiy.item.ItemRepository;
import com.qkrtprjs.happyexercise.entitiy.member.Member;
import com.qkrtprjs.happyexercise.entitiy.member.MemberRepository;
import com.qkrtprjs.happyexercise.entitiy.member.Role;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(roles = "USER")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ItemApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ItemRepository itemRepository;


    @After
    public void tearDown() throws Exception {
        itemRepository.deleteAll();
    }

    @Test
    public void item_등록() throws Exception {
        //given
        String name = "qkrtprjs";
        String detail = "qkrtprjs456";
        int price = 1;
        int stock = 1;
        ItemSaveRequestDto dto = ItemSaveRequestDto.builder()
                .name(name)
                .detail(detail)
                .price(price)
                .stock(stock)
                .build();

        final String fileName = "66e06bd7-33db-467d-857f-fbc4b20c05eb_image"; //파일명
        final String contentType = "png"; //파일타입
        final String filePath = "src/test/resources/img/" + fileName + "." + contentType; //파일경로
        FileInputStream fileInputStream = new FileInputStream(filePath);

        MockMultipartFile multipartFile = new MockMultipartFile("img", "66e06bd7-33db-467d-857f-fbc4b20c05eb_image.png", contentType,
                fileInputStream);

        String dtoJson = new ObjectMapper().writeValueAsString(dto);
        MockMultipartFile dtoMultipartFile = new MockMultipartFile("dtoMultipartFile", "dtoMultipartFile", "application/json",
                dtoJson.getBytes(StandardCharsets.UTF_8));
        System.out.println(dtoMultipartFile);
        String url = "http://localhost:" + port + "/api/item";

        //when
        mvc.perform(multipart(url)
                        .file(multipartFile)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andDo(print());
        //then

        Item item = itemRepository.findAll().get(0);

        assertThat(item.getName()).isEqualTo(name);
        assertThat(item.getDetail()).isEqualTo(detail);
        assertThat(item.getPrice()).isEqualTo(price);
        assertThat(item.getStock()).isEqualTo(stock);
        System.out.println(item.getImgName());
        System.out.println(item.getImgPath());
    }


}
