package com.qkrtprjs.happyexercise.entitiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.bytebuddy.asm.Advice;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
//@MappedSupderClass를 적요이킨 클래스를 단독으로 사용될 일이 없기때문에 abstract를 추가해서 추상클래스로 만들어준다.
//추상클래스는 선언해놓고 자식클래스에서 메서드를 완성하도록 유도하는 클래스이다.
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;
}
