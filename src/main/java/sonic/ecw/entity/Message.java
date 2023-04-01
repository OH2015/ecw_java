package sonic.ecw.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

import lombok.Getter;
import lombok.Setter;

/**
 * メッセージEntityクラス
 *
 * <ul>
 * <li>メッセージマスタの結果セットのレコードを格納するクラス。</li>
 * <li>MVCモデルにおけるM(Model)の一部。</li>
 * <li>Getter/Setterメソッドの生成はlombokで行っています。</li>
 * </ul>
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
public class Message {

	/**
	 * メッセージコード
	 */
	@Id
	@Column
	@Getter
	@Setter
	private String msgCd;

	/**
	 * 文言
	 */
	@Column
	@Getter
	@Setter
	private String msgVal;

}