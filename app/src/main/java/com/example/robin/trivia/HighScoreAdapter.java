//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.robin.trivia.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Robin on 13-3-2018.
// */
//
//public class HighScoreAdapter extends ArrayAdapter {
//
//    Context context;
//    int resource;
//    Object highScores;
//
//    public HighScoreAdapter(@NonNull Context context, int resource, Object highScores) {
//        super(context, resource);
//        this.context = context;
//        this.resource = resource;
//        this.highScores = highScores;
//    }
//
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        if (convertView == null) {
//
//            convertView = LayoutInflater.from(getContext()).inflate(resource, null);
//
//             item = highScores.getString(position);
//
//            // retrieve views from different pieces of info
//            TextView name = convertView.findViewById(R.id.name);
//            TextView price = convertView.findViewById(R.id.price);
//            ImageView pic = convertView.findViewById(R.id.foodPic);
//
//            // initiate download of the image
//            ItemPictureRequest pictureRequest = new ItemPictureRequest(context);
//            pictureRequest.getPicture(new ImageCallback(pic), item.getImageUrl());
//
//            // push the pieces of info to the view
//            name.setText(item.getName());
//            price.setText("€" + item.getPrice());
//        }
//
//        return convertView;
//    }
//}
