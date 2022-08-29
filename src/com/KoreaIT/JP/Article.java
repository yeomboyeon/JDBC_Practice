package com.KoreaIT.JP;

public class Article {
	public int id;
	public String regDate;
	public String updateDate;
	public String title;
	public String body;

	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}

	public Article(int id, String regDate, String updateDate, String title, String body) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
	}
	
	@Override // 처음 3개의 인자로 진행하였으나, 5개로 인자가 변경되면서 다시 추가하면 변경된다.
	public String toString() {
		return "Article [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", title=" + title
				+ ", body=" + body + "]";
	}

}