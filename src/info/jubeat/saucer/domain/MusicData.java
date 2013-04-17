package info.jubeat.saucer.domain;

public class MusicData {
	public String name;	// �ǰ��̸�
	public int bscl;	// bsc ����
	public int bscf;	// bsc Ǯ�޿���
	public String bsc;	// bsc ����
	public int advl;
	public int advf;
	public String adv;
	public int extl;
	public int extf;
	public String ext;
	public String info;	// �� ��ȣ(jacketXXXXXXXX�� X�κ� ���� 8�ڸ�)
	
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
	
	���̺� �и�
	* �ǰ� ���� ���̺�(�̸�, ����, Ȱ��ȭ ����) *
	CREATE TABLE musicinfo(
		mid varchar(8) primary key,
		mname varchar(80) not null,
		bscl integer default 0,
		advl integer default 0,
		extl integer default 0,
		active integer default 1
	);
	
	* ���� ���� ���̺� *
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
