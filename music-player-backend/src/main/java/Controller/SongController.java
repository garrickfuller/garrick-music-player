package Controller;

import model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import repository.SongRepository;
import services.StorageService;

import java.util.List;
import java.util.Optional;
import java.io.IOException;

@RestController
@RequestMapping("/api/songs")
public class SongController {
    private StorageService storageService;

    private SongRepository songRepository;

    @Autowired
    public SongController(StorageService storageService, SongRepository songRepository) {
        this.songRepository = songRepository;
        this.storageService = storageService;
    }

    /**
     * @return new http response with all songs
     */
    @GetMapping
    public ResponseEntity<List<Song>> getSongs() {
        return ResponseEntity.ok(songRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSong(@PathVariable String id) {
        Optional<Song> song  = songRepository.findById(id);

        if (song.isEmpty()) {
            return ResponseEntity.ok(song.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> createSong(@RequestPart("song")Song song, @RequestPart("file") MultipartFile file) throws IOException {

        //See if there is already a song with that filename
        if (songRepository.existsSongByFileNameEquals(file.getOriginalFilename()) || songRepository.existsSongByTitleEquals(song.getTitle())){
            return ResponseEntity.badRequest().body("taken");
        }else{
            System.out.println("Uploading the file...");
            storageService.uploadSong(file);

            //Saving the song data into the database
            song.setFilename(file.getOriginalFilename());
            Song insertedSong = songRepository.insert(song);

            return new ResponseEntity<>(insertedSong, HttpStatus.CREATED);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable String id, @RequestBody Song songData){

        Optional<Song> songOptional = songRepository.findById(id);

        if (songOptional.isPresent()){

            Song song = songOptional.get();

            if (songData.getTitle() != null){
                song.setTitle(songData.getTitle());
            }

            if (songData.getArtist() != null){
                song.setArtist(songData.getArtist());
            }


            songRepository.save(song);

            return ResponseEntity.ok(song);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Song> deleteSong(@PathVariable String id) {
        if (songRepository.existsById(id)) {
            songRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
