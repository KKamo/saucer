package info.jubeat.saucer.domain;

public class Player {
	public String uid;		// 라이벌아이디
	public String id;		// 게임아이디
	public String title;	// 칭호
	public String groups;	// 그룹
	public String jubility;	// 유빌리티
	public String jubilityName;	// 유빌리티 이름(jellyfish 등)
	public String plma;		// 유빌맅티 변동폭
	public String jimage;	// 유빌리티 이미지
	public String marker;	// 마커
	public String background;	// 배경
	public String lastTime;	// 최근 플레이 시간
	public String lastLocation;	// 위치
	public String playTunes;	// 플레이 튠수
	public String fullCombo;	// 풀콤보 횟수
	public String excellent;	// 엑설 횟수
	
	/* user table *
	CREATE TABLE user(
		uid varchar(20) primary key,
		id varchar(10) not null,
		title varchar(20),
		groups varchar(15),
		jimage varchar(10),
		jubility varchar(5),
		plma varchar(5),
		jubilityname varchar(15),
		marker varchar(8),
		background varchar(4),
		lasttime varchar(16),
		lastlocation varchar(20),
		playtune varchar(5),
		fullcombo varchar(4),
		excellent varchar(4)
	);
	 /* */
}
