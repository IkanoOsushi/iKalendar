package org.t_robop.ikalendar;

public class CustomListItem {
    private String mTime;
    private String mNoteText;

    //コンストラクタ
    public CustomListItem(){};

    public CustomListItem(String time, String noteText){
        mTime = time;
        mNoteText = noteText;
    }

    /**
     * 時間を設定
     * @param time 時間
     */
    public void setmTime(String time){
        mTime = time;
    }

    /**
     * 予定
     * @param noteText 予定
     */
    public void setmNoteText(String noteText){
        mNoteText = noteText;
    }

    /**
     * 時間を取得
     * @return 時間
     */
    public String getmTime(){
        return mTime;
    }

    /**
     * 予定を取得
     * @return 予定
     */
    public String getmNoteText(){
        return mNoteText;
    }
}
