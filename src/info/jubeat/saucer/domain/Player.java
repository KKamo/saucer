package info.jubeat.saucer.domain;

public class Player {
	public String uid;		// ���̹����̵�
	public String id;		// ���Ӿ��̵�
	public String title;	// Īȣ
	public String groups;	// �׷�
	public String jubility;	// ������Ƽ
	public String jubilityName;	// ������Ƽ �̸�(jellyfish ��)
	public String plma;		// ������Ƽ ������
	public String jimage;	// ������Ƽ �̹���
	public String marker;	// ��Ŀ
	public String background;	// ���
	public String lastTime;	// �ֱ� �÷��� �ð�
	public String lastLocation;	// ��ġ
	public String playTunes;	// �÷��� ƪ��
	public String fullCombo;	// Ǯ�޺� Ƚ��
	public String excellent;	// ���� Ƚ��
	
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
