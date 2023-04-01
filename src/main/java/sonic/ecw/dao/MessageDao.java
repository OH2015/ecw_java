package sonic.ecw.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import sonic.ecw.entity.Message;

/**
 * メッセージDao(Data Access Object)クラス
 *
 * <ul>
 * <li>メッセージマスタへのDBアクセスを担当するクラス。</li>
 * <li>/ecw/src/main/resources/META-INF/sonic/ecw/dao/MessageDao 配下の外部SQLを実行し、結果を受け取ります。</li>
 * <li>Doma（Java DBアクセスF/W）を使用しています。</li>
 * <li>MVCモデルにおけるM(Model)の一部。</li>
 * </ul>
 */
@Dao
@ConfigAutowireable
public interface MessageDao {

	/**
	 * メッセージリスト取得
	 * @return メッセージリスト
	 */
	@Select
	public List<Message> selectMessageList();

}