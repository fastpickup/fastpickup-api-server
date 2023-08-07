CREATE DATABASE projectdb;

CREATE USER 'projectuser'@'localhost' IDENTIFIED BY 'projectuser';

CREATE USER 'projectuser'@'%' IDENTIFIED BY 'projectuser';


GRANT ALL PRIVILEGES ON projectdb.* TO 'projectuser'@'localhost';

GRANT ALL PRIVILEGES ON projectdb.* TO 'projectuser'@'%';

ALTER USER 'projectuser'@'localhost' IDENTIFIED WITH mysql_native_password BY 'projectuser';

ALTER USER 'projectuser'@'%' IDENTIFIED WITH mysql_native_password BY 'projectuser';

##회원 테이블 생성
create table tbl_member (
	email varchar(100) not null primary key,
	memberPw varchar(200) not null,
	memberName varchar(100) not null,
	memberPhone varchar(20) not null,
	store varchar(100) not null default 'm'
)
;

##회원 권환 테이블 생성
CREATE TABLE tbl_member_role (
	email varchar(100) not null,
	rolename varchar(50) not null
)
;

##회원 로그인 유지 여부 테이블 생성
create table persistent_logins (
       username varchar(64) not null,
       series varchar(64) primary key,
       token varchar(64) not null,
       last_used timestamp not null
)
;

##좋아요 테이블 생성
CREATE TABLE tbl_like (
    pno BIGINT NOT NULL,
    email VARCHAR(100) NOT NULL,
    createDate TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (pno, email),
    FOREIGN KEY (pno) REFERENCES tbl_product(pno) ON DELETE CASCADE,
    FOREIGN KEY (email) REFERENCES tbl_member(email) ON DELETE CASCADE
)
;
select * from tbl_like;

##상품 테이블 생성
create table tbl_product (
	pno bigint auto_increment primary key,
	productName varchar(200) not null,
	productContent varchar(1000) not null,
	productPrice int not null,
	registDate timestamp default now(),
	updateDate timestamp default now(),
	viewCount int default 0,
	likeCount int default 0,
	isRecommend int,
	isDeletedProduct tinyint not null default true,
	sno bigint not null,
	FOREIGN KEY (sno) REFERENCES tbl_store(sno) ON DELETE CASCADE
)
;
delete from tbl_product;

##상품 이미지 테이블 생성
CREATE TABLE tbl_product_image (
	uuid varchar(50) PRIMARY KEY,
	fileName varchar(200) not null,
	pno bigint not null,
	ord int default 0 not null,
	FOREIGN KEY (pno) REFERENCES tbl_product(pno) ON DELETE CASCADE
)
;

##상품 카테고리 테이블 생성
create table tbl_product_category (
	cno bigint auto_increment primary key,
	categoryName varchar(100) not null,
	pno bigint not null,
	FOREIGN KEY (pno) REFERENCES tbl_product(pno) ON DELETE CASCADE
)
;

##문의 테이블 생성
create table tbl_qna (
	qno bigint auto_increment primary key,
	qnaTitle varchar(200) not null,
	qnaContent varchar(1000) not null,
	registDate timestamp default now(),
	updateDate timestamp default now(),
	email varchar(100) not null,
	FOREIGN KEY (email) REFERENCES tbl_member(email) ON DELETE CASCADE
)
;

##문의 테이블 댓글 생성
create table tbl_qna_reply (
	rno bigint auto_increment primary key,
	qno bigint not null,
	email varchar(100) not null,
	reply varchar(1000) not null,
	replyDate timestamp default now(),
	foreign key (qno) references tbl_qna(qno) ON DELETE CASCADE,
	foreign key (email) references tbl_member(email) ON DELETE CASCADE
)
;
select * from tbl_qna_reply;

##가맹점 테이블 생성
create table tbl_store (
	sno bigint auto_increment primary key,
	storeName varchar(200) not null,
	storeNumber varchar(20) not null,
	storeAddress varchar(100) not null,
	email varchar(100) not null,
	FOREIGN KEY (email) REFERENCES tbl_member(email) ON DELETE CASCADE
)
;
drop table tbl_store;

##주문 테이블 생성
create table tbl_order (
	ono bigint auto_increment primary key,
	registDate timestamp default now(),
	orderCount int default 1 not null,
	email varchar(100) not null,
	sno bigint not null,
	pno bigint not null,
	FOREIGN KEY (email) REFERENCES tbl_member(email) ON DELETE cascade,
	FOREIGN KEY (pno) REFERENCES tbl_product(pno) ON DELETE CASCADE,
	FOREIGN KEY (sno) REFERENCES tbl_store(sno) ON DELETE CASCADE
)
;
drop table tbl_order;

##주문 처리이력 테이블 생성
create table tbl_order_history (
	orderHistory bigint auto_increment primary key,
	orderStatus varchar(50) default '접수' not null,
	ono bigint not null,
	FOREIGN KEY (ono) REFERENCES tbl_order(ono) ON DELETE CASCADE
)
;
drop table tbl_order_history;

## 리뷰 테이블 생성
create table tbl_review (
   rno bigint auto_increment primary key,
   reviewContent varchar(1000) not null,
   reviewDate timestamp default now(),
   reviewUpdateDate timestamp default now(),
   gno int not null,
   email varchar(100) not null,
   sno bigint not null,
   ono bigint not null,
   isDeleted tinyint defalut 0 not null,
   FOREIGN KEY (sno) REFERENCES tbl_store(sno) ON DELETE cascade,
   FOREIGN KEY (email) REFERENCES tbl_member(email) ON DELETE cascade,
   FOREIGN KEY (ono) REFERENCES tbl_order(ono) ON DELETE CASCADE
)
;
alter table tbl_review add `isDeleted` tinyint default 0 not null after `ono`;

## 리뷰 이미지 테이블 생성
create table tbl_review_img(
   uuid varchar(500) primary key,
   fileName varchar(500) not null,
   ord int not null default 0,
   rno bigint not null,
   FOREIGN KEY (rno) REFERENCES tbl_review(rno) ON DELETE cascade
)
;


##회원
alter table tbl_member add `joinDate` timestamp default now() after `store`;

create index idx_tbl_member_joindate on tbl_member (joinDate asc);

select * from tbl_member;

select * from tbl_member_role;
update tbl_member_role set rolename = 'STORE' where email = 'thistrik@gmail.com';

select * from persistent_logins;

##Member Select
SELECT * FROM tbl_member
ORDER BY joinDate DESC
LIMIT 0, 10
;

delete from tbl_member where memberName = '이범수';

##/회원

##상품
select * from tbl_product;
select * from tbl_product_image;
select * from tbl_product_category;

select * from tbl_product_image where pno = 45;
delete from tbl_product_category where cno = 34;

delete from tbl_product;
delete from tbl_product_category;

delete from tbl_product where pno = 12;

##등록
insert into tbl_product
    (productName, productContent, productPrice, isRecommend, sno)
    values ('허니콤보', '허니콤보, 순살, 오리지날', 20000, 0, 1)
;
insert into tbl_product_category
	(categoryName, pno)
	values ('치킨', 38)
;

##리스트
select prdt.pno, prdt.productName, prdt.productPrice, prdt.registDate, prdt.viewCount,
           prdt.likeCount, prdt.isRecommend, prdt.isDeletedProduct, prdt.storeName, prdt.sno,
           prdt.categoryName, concat(tpi.uuid,'_',tpi.fileName) as fileName
    from (
            select tp.pno, tp.productName, tp.productPrice, tp.registDate, tp.viewCount,
                   tp.likeCount, tp.isRecommend, tp.isDeletedProduct, ts.storeName, ts.sno,
                   tpc.categoryName
            from tbl_product tp
              left outer join tbl_store ts on ts.sno = tp.sno
              left outer join tbl_product_category tpc on tpc.pno = tp.pno
            where tp.pno > 0 and tp.isDeletedProduct = true
            order by tp.pno desc
            limit 0, 10
        ) as prdt
    left outer join tbl_product_image tpi
    on tpi.pno = prdt.pno
    and (tpi.ord = 0 or tpi.ord is null)
    order by prdt.pno desc
;

##가맹점 상품 리스트
select prdt.pno, prdt.productName, prdt.productPrice, prdt.registDate, prdt.viewCount,
           prdt.likeCount, prdt.isRecommend, prdt.isDeletedProduct, prdt.storeName, prdt.sno,
           prdt.categoryName, concat(tpi.uuid,'_',tpi.fileName) as fileName
    from (
            select tp.pno, tp.productName, tp.productPrice, tp.registDate, tp.viewCount,
                   tp.likeCount, tp.isRecommend, tp.isDeletedProduct, ts.storeName, ts.sno,
                   tpc.categoryName
            from tbl_product tp
              left outer join tbl_store ts on ts.sno = tp.sno
              left outer join tbl_product_category tpc on tpc.pno = tp.pno
            where tp.pno > 0 and tp.isDeletedProduct = true
            order by tp.pno desc
            limit 0, 10
        ) as prdt
    left outer join tbl_product_image tpi
    on tpi.pno = prdt.pno
    and (tpi.ord = 0 or tpi.ord is null)
    where prdt.sno = 1
    order by prdt.pno desc
;

##조회
SELECT tp.pno, tp.productName, tp.productContent, tp.productPrice, tp.registDate,
    tp.updateDate, tp.likeCount, tp.isRecommend, tp.isDeletedProduct,
    ts.storeName, tpc.categoryName, CONCAT(tpi.uuid, '_', tpi.fileName) as fileNames
FROM tbl_product tp
LEFT OUTER JOIN tbl_store ts ON ts.sno = tp.sno
LEFT OUTER JOIN tbl_product_category tpc ON tpc.pno = tp.pno
LEFT OUTER JOIN tbl_product_image tpi ON tpi.pno = tp.pno
WHERE tp.pno = 22
;

##업데이트
update tbl_product tp
set
  tp.productName = '테스트', tp.productContent = '테스트', tp.productPrice = 6000, tp.updateDate = now(), tp.isRecommend = '99'
where tp.pno = 6
;


##/상품

##문의
select * from tbl_qna;
select * from tbl_qna_reply;
delete from tbl_qna_reply where qno = 22;
##/문의

##가맹점
select * from tbl_store;

insert into tbl_store
(storeName, storeNumber, storeAddress, storePhone, email)
values ('김밥천국', '123-55-88778', '서울시 종로2가', '02-7878-8787', 'thistrik@gmail.com')
;

update tbl_store
set
	storeNumber = '332-11-88752'
where sno = 2
;

alter table tbl_store add `registDate` timestamp default now() after `storePhone`;
##/가맹점

##주문
select * from tbl_order;
select * from tbl_order_history;

select * from tbl_order where registDate <= '2023-07-28';

insert into tbl_order
(orderCount, email, sno, pno)
values (6, 'thistrik@gmail.com', 31, 22)
;

insert into tbl_order_history
(orderStatus, ono)
values ('반려', 50)
;

##/주문

SELECT o.ono, o.email, o.registDate, s.storeName, o.orderCount, h.orderStatus, p.productName, p.productPrice, 
CONCAT(pi.uuid, '_', pi.fileName) as fileName 
FROM tbl_order o 
	LEFT OUTER JOIN tbl_order_history h ON o.ono = h.ono 
	LEFT OUTER JOIN tbl_product p ON o.pno = p.pno 
	LEFT OUTER JOIN tbl_product_image pi ON pi.pno = p.pno AND pi.ord = 0
	LEFT OUTER JOIN tbl_store s ON o.sno = s.sno
WHERE o.ono > 0 AND h.ono > 0 
AND ( s.storename like concat('%', '교촌', '%') 
AND o.registDate >= '2023-07-19' AND o.registDate <= '2023-07-31' ) 
 ORDER BY o.ono DESC, h.orderHistory DESC LIMIT 0, 10
;

##좋아요
select * from tbl_like;
##/좋아요

