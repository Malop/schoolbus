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
		// �Զ�����ͼ
		ListItemView listItemView = null;

		if (convertView == null) {
			// ��ȡlist_item�����ļ�����ͼ
			convertView = listContainer.inflate(this.itemViewResource, null);
			listItemView = new ListItemView();
			// ��ȡ�ؼ�����
			listItemView.time = (TextView) convertView
					.findViewById(R.id.list_item_time);
			listItemView.route = (TextView) convertView
					.findViewById(R.id.list_item_route);
			listItemView.state = (TextView) convertView
					.findViewById(R.id.list_item_state);

			// ���ÿؼ�����convertView
			convertView.setTag(listItemView);
		} else {
			listItemView = (ListItemView) convertView.getTag();
		}

		// �������ֺ�ͼƬ
		Bus bus = listItems.get(position);
		listItemView.time.setText(bus.getTime());
		listItemView.route.setText(bus.getRoute());

		// �ж��Ƿ��Ѿ�����
		if (bus.getState()) {
			listItemView.state.setText("�ѷ���");
		} else {
			listItemView.state.setText("δ����");
		}

		return convertView;
	}

}
