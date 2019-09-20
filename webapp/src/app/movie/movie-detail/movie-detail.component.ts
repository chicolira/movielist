import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Movie } from 'src/app/_shared/model/movie';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.scss']
})
export class MovieDetailComponent implements OnInit {

  movie = new Movie();

  constructor(private router: Router) {

    if (!this.router.getCurrentNavigation().extras.state) {
      this.router.navigate(['']);
      return;
    }

    this.movie = this.router.getCurrentNavigation().extras.state.movie;
  }

  ngOnInit() {
  }

  movieImageSrc(movie: Movie) {
    return 'https://image.tmdb.org/t/p/original/' + movie.backdrop_path;
  }

  getBackGroundUrl() {
    return 'https://image.tmdb.org/t/p/original/' + this.movie.backdrop_path;
  }

  movieGenresAsString(movie: Movie) {

    if (movie.genres.length === 0) {
      return '';
    } else if (movie.genres.length === 1) {
      return movie.genres[0].name;
    }

    return movie.genres.map((g) => g.name).reduce((acc, cur) => acc.concat(`, ${cur}`));
  }

}
