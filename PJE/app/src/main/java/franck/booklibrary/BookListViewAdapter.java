package franck.booklibrary;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by franc on 26/09/2015.
 */
public class BookListViewAdapter extends ArrayAdapter<Book> {
    protected Context context;
    protected LayoutInflater inflater;
    protected List<Book> books;
    protected SparseBooleanArray selectedItemsIds;

    public BookListViewAdapter(Context context, int resourceId, List<Book> books) {
        super(context, resourceId, books);
        selectedItemsIds = new SparseBooleanArray();
        this.context = context;
        this.books = books;
        inflater = LayoutInflater.from(context);
    }

    private class ViewHolder {
        ImageView imgBook;
        TextView title;
        TextView author;
        TextView isbn;
    }

    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.activity_book, null);
            holder.imgBook = (ImageView) view.findViewById(R.id.imgBook);
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.author = (TextView) view.findViewById(R.id.author);
            holder.isbn = (TextView) view.findViewById(R.id.isbn);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.title.setText(books.get(position).getTitle());
        holder.author.setText(books.get(position).getAuthor());
        holder.isbn.setText(books.get(position).getIsbn());
        holder.imgBook.setImageResource(R.drawable.book);
        return view;
    }

    @Override
    public void remove(Book book) {
        books.remove(book);
        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        selectView(position, !selectedItemsIds.get(position));
    }

    public void removeSelection() {
        selectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            selectedItemsIds.put(position, value);
        else
            selectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public SparseBooleanArray getSelectedIds() {
        return selectedItemsIds;
    }
}
