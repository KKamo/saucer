package info.jubeat.saucer.domain;

public class MusicData {
	public String name;	// 악곡이름
	public int bscl;	// bsc 레벨
	public int bscf;	// bsc 풀콤여부
	public String bsc;	// bsc 점수
	public int advl;
	public int advf;
	public String adv;
	public int extl;
	public int extf;
	public String ext;
	public String info;	// 곡 번호(jacketXXXXXXXX의 X부분 숫자 8자리)
	
	/* music data table *
	CREATE TABLE jubeat(
		name varchar(80) not null,
		bscl integer default 0,
		bscf integer default 0,
		bsc varchar(8) default '-',
		advl integer default 0,
		advf integer default 0,
		adv varchar(8) default '-',
		extl integer default 0,
		extf integer default 0,
		ext varchar(8) default '-',
		info varchar(8) primary key,
		active int default 1
	);
	
	테이블 분리
	* 악곡 정보 테이블(이름, 레벨, 활성화 여부) *
	CREATE TABLE musicinfo(
		mid varchar(8) primary key,
		mname varchar(80) not null,
		bscl integer default 0,
		advl integer default 0,
		extl integer default 0,
		active integer default 1
	);
	
	* 개인 점수 테이블 *
	CREATE TABLE score(
		uid varchar(20),
		mid varchar(8),
		bscf integer default 0,
		bsc varchar(8) default '-',
		advf integer default 0,
		adv varchar(8) default '-',
		extf integer default 0,
		ext varchar(8) default '-',
		foreign key(uid) references user(uid)
		on delete cascade
		on update cascade,
		foreign key(mid) references score(mid)
		on delete cascade
		on update cascade,
		primary key(uid, mid)
	);
	
	/* */
}
