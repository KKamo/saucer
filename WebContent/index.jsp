<%@ page
	import="info.jubeat.saucer.Parse"
	import="java.util.Iterator,java.util.ArrayList"
	import="info.jubeat.saucer.domain.*"
	language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	 %><%!
	public String rating(String score) {
		if(score.equalsIgnoreCase("-"))
			return "";
		int sc = Integer.parseInt(score);
		String tag = "<img src=/saucer/res/rate/";

		if (sc >= 0 && sc < 500000)				tag += "r0.gif"; // E
		else if (sc >= 500000 && sc < 700000)	tag += "r1.gif"; // D
		else if (sc >= 700000 && sc < 800000)	tag += "r2.gif"; // C
		else if (sc >= 800000 && sc < 850000)	tag += "r3.gif"; // B
		else if (sc >= 850000 && sc < 900000)	tag += "r4.gif"; // A
		else if (sc >= 900000 && sc < 950000)	tag += "r5.gif"; // S
		else if (sc >= 950000 && sc < 980000)	tag += "r6.gif"; // SS
		else if (sc >= 980000 && sc < 1000000)	tag += "r7.gif"; // SSS
		else if (sc == 1000000)					tag += "r8.gif"; // EXC
		
		tag += " class=rate>";
		return tag;
	}

	public int[][] statistic(Iterator<MusicData> it) {
		int[][] score_data = new int[13][2];
		
		while(it.hasNext()) {
			int bsc = 0, adv = 0, ext = 0;
			MusicData md = (MusicData)it.next();
			
			String score = md.bsc;
			int level = md.bscl;
			if(!(score.equalsIgnoreCase("-"))) {
				int scr = Integer.parseInt(score);
				score_data[10][0] += scr;
				score_data[10][1]++;
				
				switch(level) {
					case 1: score_data[0][0] += scr; score_data[0][1]++; break;
					case 2: score_data[1][0] += scr; score_data[1][1]++; break;
					case 3: score_data[2][0] += scr; score_data[2][1]++; break;
					case 4: score_data[3][0] += scr; score_data[3][1]++; break;
					case 5: score_data[4][0] += scr; score_data[4][1]++; break;
					case 6: score_data[5][0] += scr; score_data[5][1]++; break;
					case 7: score_data[6][0] += scr; score_data[6][1]++; break;
					case 8: score_data[7][0] += scr; score_data[7][1]++; break;
					case 9: score_data[8][0] += scr; score_data[8][1]++; break;
					case 10: score_data[9][0] += scr; score_data[9][1]++; break;
					default: System.out.println("통계 이상");
				}
			}
			
			score = md.adv;
			level = md.advl;
			if(!(score.equalsIgnoreCase("-"))) {
				int scr = Integer.parseInt(score);
				score_data[11][0] += scr;
				score_data[11][1]++;
				
				switch(level) {
					case 1: score_data[0][0] += scr; score_data[0][1]++; break;
					case 2: score_data[1][0] += scr; score_data[1][1]++; break;
					case 3: score_data[2][0] += scr; score_data[2][1]++; break;
					case 4: score_data[3][0] += scr; score_data[3][1]++; break;
					case 5: score_data[4][0] += scr; score_data[4][1]++; break;
					case 6: score_data[5][0] += scr; score_data[5][1]++; break;
					case 7: score_data[6][0] += scr; score_data[6][1]++; break;
					case 8: score_data[7][0] += scr; score_data[7][1]++; break;
					case 9: score_data[8][0] += scr; score_data[8][1]++; break;
					case 10: score_data[9][0] += scr; score_data[9][1]++; break;
					default: System.out.println("통계 이상");
				}
			}
			
			score = md.ext;
			level = md.extl;
			if(!(score.equalsIgnoreCase("-"))) {
				int scr = Integer.parseInt(score);
				score_data[12][0] += scr;
				score_data[12][1]++;
				
				switch(level) {
					case 1: score_data[0][0] += scr; score_data[0][1]++; break;
					case 2: score_data[1][0] += scr; score_data[1][1]++; break;
					case 3: score_data[2][0] += scr; score_data[2][1]++; break;
					case 4: score_data[3][0] += scr; score_data[3][1]++; break;
					case 5: score_data[4][0] += scr; score_data[4][1]++; break;
					case 6: score_data[5][0] += scr; score_data[5][1]++; break;
					case 7: score_data[6][0] += scr; score_data[6][1]++; break;
					case 8: score_data[7][0] += scr; score_data[7][1]++; break;
					case 9: score_data[8][0] += scr; score_data[8][1]++; break;
					case 10: score_data[9][0] += scr; score_data[9][1]++; break;
					default: System.out.println("통계 이상");
				}
			}
		}
		return score_data;
	}
	
	public String addComma(int score) {
		String scr = Integer.toString(score), ret = "";
		int i, len = scr.length();
		for(i=len; i>3; i-=3)
			ret = "," + scr.substring(i-3, i) + ret;
		ret = scr.substring(0, i) + ret;
		
		return ret;
	}
	
	public String addComma(String score) {
		String scr = score, ret = "";
		int i, len = scr.length();
		for(i=len; i>3; i-=3)
			ret = "," + scr.substring(i-3, i) + ret;
		ret = scr.substring(0, i) + ret;
		
		return ret;
	}
	
	public int getAverage(int sum, int count) {
		if(count == 0)
			return 0;
		else
			return sum/count;
	}
	
%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/saucer/main.css" rel="stylesheet" type="text/css">
<title>saucer info</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>
	function refresh() {
		$.ajax({
			type: 'GET',
			url: location.href + 'refresh',
			dataType: 'json',
			success: function(data) {
			}
		});
		alert("갱신요청을 했습니다.");
	}
	
	function togate() {
		window.open('http://p.eagate.573.jp/game/jubeat/saucer/p/playdata/index.html');
	}
	
	function toggle(e) {
		if(e.style.display == "none") {
			e.style.display = "";
			document.getElementById("hide").innerHTML = "▲";
			}
		else {
			e.style.display = "none";
			document.getElementById("hide").innerHTML = "▼";
		}
	}
</script>
</head>
<body><%
	Parse parse = new Parse("http://p.eagate.573.jp/game/jubeat/saucer/p/playdata/index.html");
	Player p = parse.getPlayer();%>
<div id=all>
	<div id=head>
		<div id=title_head class=bg_gra>
			<span style='color: skyblue; font-size:14px;'><%=p.title%></span><br>
			<span style='color: white; font-size:16px; font-weight: bold;'><%=p.id%></span>
		</div>
		<div id=jubility>
			<div><img src=/saucer/res/jby/<%=p.jimage%>.png></div>
			<table class=jtable>
				<tbody>
					<tr class=top>
						<td align=left><%=p.jubilityName%></td>
						<td align=right><%=p.jubility%></td>
					</tr><tr class=cnt>
						<td align=left class=left>jubility</td>
						<td align=right class=right>이전 대비<%=p.plma%>&nbsp;</td>
					</tr><tr class=btm>
						<td colspan=2><div><div class=imgs><img src=/saucer/res/left.gif></div>
							최근 플레이 : <%=p.lastTime%><br><%=p.lastLocation%><div id=clear></div></div></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id=player>
			<div id=underLine>라이벌 ID : 숨김<!-- <%=p.uid%> --></div>
			<div id=underLine>소속 그룹 : <%=p.groups%></div>
			<div id=tuneinfo>
				<table class=play><tbody>
					<tr class=tune><td class=td_btn>play tune수</td><td align=right><%=p.playTunes%> TUNE</td></tr>
					<tr class=tune><td class=td_btn>coin 수</td><td align=right><%=Integer.parseInt(p.playTunes) / 3 + ""%> 개</td></tr>
					<tr class=tune><td class=td_btn>Full Combo수</td><td align=right><%=p.fullCombo%> 회</td></tr>
					<tr class=tune><td class=td_btn>Excellent수</td><td align=right><%=p.excellent%> 회</td></tr>
				</tbody></table>
			</div>
			<div id=setting>
				<div id=marker>마커<img src=/saucer/res/marker/<%=p.marker%>.png></div>
				<div id=bg>배경<img src=/saucer/res/bg/<%=p.background%>.gif></div>
			</div>
			<div id=refresh onclick=javascript:refresh()></div>
			<div id=toeagate onclick=javascript:togate()></div>
		</div>
	</div>
	<div id=clear></div>
	<div id=data>
		<div id=title_head class=bg_gra>
			<span style='color: white; font-size: 30px;'>PLAYDATA</span>
		</div>
		<div id=statistic_data>
		<%
			boolean odd = false;
			ArrayList<MusicData> rs = parse.getAllMusic(p.uid);
			Iterator<MusicData> it = rs.iterator();
			
			int[][] sum = statistic(it);
		%>
			<div onclick=javascript:toggle(statits) style='cursor: pointer;'>
				<div id=hide>▲</div>
				<table class=stat><tbody>
					<tr class=data_head>
						<th class=score>구분</th><th class=score>총점</th><th class=score>개수</th><th class=score>평균</th>
					</tr>
				</tbody></table>
			</div>
			<div id=statits>
				<table class=stat><tbody>
					<tr class=odd>
						<th>bsc</th><td><%=addComma(sum[10][0])%></td><td><%=sum[10][1]%></td><td><%=getAverage(sum[10][0],sum[10][1])%></td>
					</tr><tr class=even>
						<th>adv</th><td><%=addComma(sum[11][0])%></td><td><%=sum[11][1]%></td><td><%=getAverage(sum[11][0],sum[11][1])%></td>
					</tr><tr class=odd>
						<th>ext</th><td><%=addComma(sum[12][0])%></td><td><%=sum[12][1]%></td><td><%=getAverage(sum[12][0],sum[12][1])%></td>
					</tr><tr class=line>
					<th class=score></th><th class=score></th><th class=score></th><th class=score></th>
					<%
						for(int i=0; i<10; i++) {
							if(odd)
								out.println("					</tr><tr class=odd>");
							else
								out.println("					</tr><tr class=even>");
							odd = !odd;
							
							int avr = 0;
							if(sum[i][1] != 0)
								avr = sum[i][0] / sum[i][1];
							
							out.print("						<th>" + (i+1) + "</th>");
							out.print("<td>" + addComma(sum[i][0]) + "</td>");
							out.print("<td>" + sum[i][1] + "</td>");
							out.println("<td>" + getAverage(sum[i][0],sum[i][1]) + "</td>");
						}
					%>
					</tr><tr class=line>
					<th class=score></th><th class=score></th><th class=score></th><th class=score></th>
					<%
						if(odd)
							out.println("				</tr><tr class=odd>");
						else
							out.println("				</tr><tr class=even>");
						
						int totalSum = sum[10][0]+sum[11][0]+sum[12][0];
						int totalCnt = sum[10][1]+sum[11][1]+sum[12][1];
					%>
						<th>TOTAL</th><td><%=addComma(totalSum)%></td><td><%=totalCnt%></td><td><%=getAverage(totalSum,totalCnt)%></td>
					</tr>
				</tbody></table>
			</div>
		</div>
		
		<table class=music_data><tbody>
			<tr class=data_head>
				<th class=title colspan="2"><img src=/saucer/res/name.gif></th>
				<th class=score colspan="2"><img src=/saucer/res/bsc.gif></th>
				<th class=score colspan="2"><img src=/saucer/res/adv.gif></th>
				<th class=score colspan="2"><img src=/saucer/res/ext.gif></th>
				<%
				rs = parse.getAllMusic(p.uid);
				it = rs.iterator();
				
				odd = true;
				
				while (it.hasNext()) {
					MusicData md = (MusicData) it.next();
					if (odd)
						out.println("		</tr><tr class=odd>");
					else
						out.println("		</tr><tr class=even>");
					odd = !odd;
					
					out.println("			<td class=jacket><img class=cover src=/saucer/res/jacket/" + md.info + ".gif></td>");
					out.println("			<td class=name style='text-align: left;'>" + md.name + "</td>");
					
					out.print("			<td class=bscl>" + md.bscl + "</td><td>" + addComma(md.bsc));
					if(md.bscf == 1)
						out.print("<div id=f1>");
					else
						out.print("<div id=f0>");
					out.println(rating(md.bsc) + "</div></td>");
					
					out.print("			<td class=advl>" + md.advl + "</td><td>" + addComma(md.adv));
					if(md.advf == 1)
						out.print("<div id=f1>");
					else
						out.print("<div id=f0>");
					out.println(rating(md.adv) + "</div></td>");
					
					out.print("			<td class=extl>" + md.extl + "</td><td>" + addComma(md.ext));
					if(md.extf == 1)
						out.print("<div id=f1>"); 
					else
						out.print("<div id=f0>");
					out.println(rating(md.ext) + "</div></td>");
				}%>
			</tr>
		</tbody></table>
	</div>
</div>
</body>
</html>