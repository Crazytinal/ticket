package experiment.ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by 志锋 on 2017/5/19.
 */
public class CinemaInfoActivity extends Activity {
    private List<HashMap<String, Object>> filmListItem;
    private String[] filmName = { "毒诫", "摔跤吧爸爸", "银河护卫队2", "妖精的尾巴-DRAGON CRY", "加勒比海盗5" };
    private String[] director = { "刘国昌", "尼特什·提瓦瑞", "詹姆斯·古恩", "真岛", "乔阿吉姆·罗恩尼"};
    private String[] starring = { "刘青云 ，古天乐", "阿米尔·汗", "克里斯·帕拉特", " 纳兹",
            "约翰尼·德普" };
    private String[] produce = { "中国", "印度", "美国", "日本", "美国" };
    private String[] actionTime = { "2017-05-12", "2017-05-05", "2017-05-05",
            "2017-05-06", "2017-05-26" };
    private String[] summary = { "很好看的电影！", "很好看的电影！", "很好看的电影！", "很好看的电影！",
            "很好看的电影！" };
    private List<FilmInfo> filmsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cinema_info_activity);
        initFilmInfos();
        initList();
    }

    private void initFilmInfos() {
        filmsList = new ArrayList<FilmInfo>();
        for (int i = 0; i < filmName.length; i++) {
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setFilmName(filmName[i]);
            filmInfo.setDirector(director[i]);
            filmInfo.setStarring(starring[i]);
            filmInfo.setProduce(produce[i]);
            filmInfo.setSummary(summary[i]);
            filmInfo.setActionTime(actionTime[i]);
            if (i == 0) {
                filmInfo.setImgUrl(R.drawable.image1 + "");
            } else if (i == 1) {
                filmInfo.setImgUrl(R.drawable.image2 + "");
            } else if (i == 2) {
                filmInfo.setImgUrl(R.drawable.image3 + "");
            } else if (i == 3) {
                filmInfo.setImgUrl(R.drawable.image4 + "");
            } else if (i == 4) {
                filmInfo.setImgUrl(R.drawable.image5 + "");
            }
            filmsList.add(filmInfo);
        }
    }

    private void initList() {
        filmListItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < filmsList.size(); i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("imgUrl", Integer.valueOf(filmsList.get(i).getImgUrl()));
            map.put("filmName", filmsList.get(i).getFilmName());
            map.put("director", getString(R.string.daoyan)
                    + filmsList.get(i).getDirector());
            map.put("starring", getString(R.string.zhuyan)
                    + filmsList.get(i).getStarring());

            filmListItem.add(map);
        }
        SimpleAdapter listItemAdapter = new SimpleAdapter(this, filmListItem,
                R.layout.film_list_listview, new String[] { "imgUrl",
                        "filmName", "director", "starring", }, new int[] {
                        R.id.imgUrl, R.id.film_name, R.id.daoyan, R.id.zhuyan });
        ListView filmListView = (ListView) findViewById(R.id.filmListView);
        filmListView.setAdapter(listItemAdapter);
        filmListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent = new Intent(CinemaInfoActivity.this, FilmInfoActivity.class);
                intent.putExtra("filmInfo", filmsList.get(arg2));
                startActivity(intent);
            }
        });
    }
}
