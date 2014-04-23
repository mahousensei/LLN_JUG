package com.tritschler.jcep;

import java.util.Date;

public final class Quote {

	private Date date;
	private Double closePrice;
	
	public Quote(Date date, Double px) {
		this.date = date;
		this.closePrice = px;
	}

	public Date getDate() {
		return date;
	}
	public Double getClosePrice() {
		return closePrice;
	}
	
	@Override
	public String toString() {
		return "Quote [date=" + date + ", closePrice=" + closePrice + "]";
	}
	
}
