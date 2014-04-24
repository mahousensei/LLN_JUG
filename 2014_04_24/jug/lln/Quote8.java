package jug.lln;

import java.util.LocalDate;

public final class Quote8 {

	private LocalDate date;
	private Double closePrice;
	
	public Quote(LocalDate date, Double px) {
		this.date = date;
		this.closePrice = px;
	}

	public LocalDate getDate() {
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
