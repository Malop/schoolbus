package jh.app.adapter;

import java.util.List;

import jh.app.bean.Bus;
import jh.app.schoolbus.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewBusAdapter extends BaseAdapter {

	private Context context;
	private List<Bus> listItems;
	private int itemViewResource;
	private LayoutInflater listContainer;

	static class ListItemView {
		public TextView time;
		public TextView state;
		public TextView route;
	}

	public ListViewBusAdapter(Context context, List<Bus> data, int resource) {
		this.context = context;
		this.listItems = data;
		this.itemViewResource = resource;
		listContainer = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listItems.size();
	}

	@Override
	public Object getItem(int arg0) {
		return listItems.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 自定义视图
		ListItemView listItemView = null;

		if (convertView == null) {
			// 获取list_item布局文件的视图
			convertView = listContainer.inflate(this.itemViewResource, null);
			listItemView = new ListItemView();
			// 获取控件对象
			listItemView.time = (TextView) convertView
					.findViewById(R.id.list_item_time);
			listItemView.route = (TextView) convertView
					.findViewById(R.id.list_item_route);
			listItemView.state = (TextView) convertView
					.findViewById(R.id.list_item_state);

			// 设置控件集到convertView
			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		// 设置文字和图片
		Bus bus = listItems.get(position);
		listItemView.time.setText(bus.getTime());
		listItemView.route.setText(bus.getRoute());

		// 判断是否已经发车
		if (bus.getState()) {
			listItemView.state.setText("已发车");
		} else {
			listItemView.state.setText("未发车");
		}

		return convertView;
	}

}
