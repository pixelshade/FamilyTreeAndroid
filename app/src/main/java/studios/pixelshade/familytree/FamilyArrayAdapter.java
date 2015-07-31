package studios.pixelshade.familytree;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

/**
 * Created by pixelshade on 30/07/15.
 */
public class FamilyArrayAdapter extends ArrayAdapter<Person>{
    private final Context context;
    private final List<Person> people;

    static class ViewHolder{
        public static ImageView image;
        public static TextView text;
    }

    public FamilyArrayAdapter(Context context, int resource, List<Person> objects) {
        super(context, resource, objects);
        this.context = context;
        this.people = objects;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            rowView = inflater.inflate(R.layout.person_cell_layout, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.personNameTV);
            viewHolder.image = (ImageView) rowView.findViewById(R.id.personIV);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();

        Person p  = people.get(position);
        holder.text.setText(p.name);
        String filePath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator + p.imageName;
        Bitmap bmp = BitmapFactory.decodeFile(filePath);
        holder.image.setImageBitmap(bmp);

        return rowView;
    }

}
