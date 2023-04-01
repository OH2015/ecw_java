package sonic.ecw.entity;


import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

import lombok.Getter;
import lombok.Setter;


@Entity(naming = NamingType.SNAKE_LOWER_CASE)
public class Order {

	/**
	 * 件数
	 */
	@Getter
	@Setter
	private Integer count;

	@Getter
	@Setter
	private String orderDate;

	@Getter
	@Setter
	private String orderMonth;


	@Getter
	@Setter
	private String orderQuantityDaily;

	@Getter
	@Setter
	private String earningsDaily;

	@Getter
	@Setter
	private String orderQuantityMonthly;

	@Getter
	@Setter
	private String earningsMonthly;

}