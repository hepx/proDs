package weibo4j.examples.timeline;

import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

public class GetUserTimeline {

//	public static void main(String[] args) {
//		String access_token = args[0];
//		Timeline tm = new Timeline();
//		tm.client.setToken(access_token);
//		try {
//			StatusWapper status = tm.getUserTimeline();
//			for(Status s : status.getStatuses()){
//				Log.logInfo(s.toString());
//			}
//			System.out.println(status.getNextCursor());
//			System.out.println(status.getPreviousCursor());
//			System.out.println(status.getTotalNumber());
//			System.out.println(status.getHasvisible());
//		} catch (WeiboException e) {
//			e.printStackTrace();
//		}
//	总是这样对我[泪]真心恋上你的文字，总是写得那么窝心~[爱你]值得一看！
//
//	+++++++++++++
//	反复问同一个问题，说谎嫌疑大，识破谎言就看这个。
//
//	+++++++++++++
//	液晶屏幕坏点修复小技巧，这么实用的帖子，收藏吧！
//
//	+++++++++++++
//	}
	
	public static void main(String[] args) {
		String screen_name="大搞笑时代";
		String uid="2405096864";
		String since_id="0";
		int feature=2;
		int limit=3;
		String access_token = WeiboConfig.getValue("access_token");
		Timeline tm = new Timeline();
		tm.client.setToken(access_token);
		try {
			StatusWapper status = tm.getUserTimelineByUid(uid, since_id, feature,limit);
			//StatusWapper status=tm.getUserTimelineByName(screen_name);
			for(Status s : status.getStatuses()){
				String text=s.getText();
				String pic=s.getBmiddlePic();
				if((text==null||"".equals(text))&&s.getRetweetedStatus()!=null){
					text=s.getRetweetedStatus().getText();
				}
				if ((pic==null||"".equals(pic))&&s.getRetweetedStatus()!=null){
					pic=s.getRetweetedStatus().getBmiddlePic();
				}
				System.out.println(text);
				System.out.println(pic);
				System.out.println("+++++++++++++");
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
