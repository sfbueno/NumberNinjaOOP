import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.trial.R

class CarouselRVAdapter(private val carouselDataList: List<Int>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class CarouselItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageButton: ImageButton = view.findViewById(R.id.imageButton)
    }

    class CarouselItemViewHolder2(view: View) : RecyclerView.ViewHolder(view) {
        val imageButton: ImageButton = view.findViewById(R.id.imageButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ITEM1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item2, parent, false)
                CarouselItemViewHolder(view)
            }
            TYPE_ITEM2 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
                CarouselItemViewHolder2(view)
            }
            TYPE_ITEM3 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.ekalam, parent, false)
                CarouselItemViewHolder2(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val resourceId = carouselDataList[position]
        when (holder) {
            is CarouselItemViewHolder -> holder.imageButton.setImageResource(resourceId)
            is CarouselItemViewHolder2 -> holder.imageButton.setImageResource(resourceId)
        }
    }

    override fun getItemCount(): Int {
        return carouselDataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position % 3 == 0 -> TYPE_ITEM1
            position % 3 == 1 -> TYPE_ITEM2
            position % 3 == 2 -> TYPE_ITEM3
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    companion object {
        private const val TYPE_ITEM1 = 0
        private const val TYPE_ITEM2 = 1
        private const val TYPE_ITEM3 = 2
    }
}
