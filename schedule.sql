CREATE TABLE schedule (
    id INT NOT NULL PRIMARY KEY,
    password VARCHAR(20) NOT NULL,
    name VARCHAR(10) NOT NULL,
    title VARCHAR(20) NOT NULL,
    contents VARCHAR(200),
    createdDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedDate  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



-- 일정 생성
INSERT INTO schedule (name, password, title, contents)
    VALUES ('홍길동', 'qwer1234', '내일배움캠프', '');


-- 전체 일정 조회
SELECT id, name, title, updatedDate FROM schedule
ORDER BY updatedDate DESC;


-- 선택 일정 조회
SELECT name, title, contents, updatedDate FROM schedule
WHERE id = '';

-- 선택 일정 수정
UPDATE schedule SET name = '',
                    contents = ''
WHERE id = '' AND password = '';

-- 선택 일정 삭제
DELETE FROM schedule
WHERE id = '' AND password = '';
