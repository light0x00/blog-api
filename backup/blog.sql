
create database blog;

use blog;

create table msg_comment(
  id int primary key auto_increment,
  article_key varchar(100),
  nickname varchar(30),
  email varchar(50),
  post_date datetime,
  content varchar(200),
  is_deleted boolean
)

CREATE INDEX msg_comment_article_key_index ON msg_comment (article_key);