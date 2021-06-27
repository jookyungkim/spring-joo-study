package study.jpashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.jpashop.domin.item.Album;
import study.jpashop.domin.item.Book;
import study.jpashop.domin.item.Item;
import study.jpashop.domin.item.Movie;
import study.jpashop.repository.ItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = false)
    public Long save(Item item) throws Exception {
        itemRepository.save(item);
        return item.getId();
    }

    @Transactional(readOnly = false)
    public Long updateItem(Long id, Item item) {
        Item itemOne = itemRepository.findById(id).get();
        itemOne.itemCheangAll(item.getName(), item.getPrice(), item.getStockQuantity());
        return itemOne.getId();
    }

    @Transactional(readOnly = false)
    public Long updateBook(Long id, Book book) {
        Book item = (Book) itemRepository.findById(id).get();
        item.itemCheangAll(book.getName(), book.getPrice(), book.getStockQuantity());
        item.bookCheangAll(book.getAuthor(), book.getIsbn());
        return item.getId();
    }

    @Transactional(readOnly = false)
    public Long updateMovie(Long id, Movie movie) {
        Movie item = (Movie) itemRepository.findById(id).get();
        item.itemCheangAll(movie.getName(), movie.getPrice(), movie.getStockQuantity());
        item.movieCheangAll(movie.getAirector(), movie.getActor());
        return item.getId();
    }

    @Transactional(readOnly = false)
    public Long updateAlbum(Long id, Album album) {
        Album item = (Album) itemRepository.findById(id).get();
        item.itemCheangAll(album.getName(), album.getPrice(), album.getStockQuantity());
        item.albumCheangAll(album.getArtist(), album.getEtc());
        return item.getId();
    }

    public Optional<Item> findById(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item;
    }

    public List<Item> findItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }
}
