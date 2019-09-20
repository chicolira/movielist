import { MovieList } from 'src/app/_shared/model/movie-list';
import { MovieService } from './../movie.service';
import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/app/_shared/model/movie';
import { Router } from '@angular/router';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.scss']
})
export class MovieListComponent implements OnInit {

  movieList: MovieList;
  focusMovie: Movie = new Movie();
  searchInput = '';
  waitingRequest = false;
  pageCounter = 1;
  scrollDistance = 1;
  scrollThrottle = 100;

  constructor(
    private movieService: MovieService,
    private router: Router
  ) {
    this.movieList = new MovieList();
  }

  ngOnInit() {
    this.loadMovies();
  }

  movieGenresAsString(movie: Movie) {

    if (movie.genres.length === 0) {
      return '';
    } else if (movie.genres.length === 1) {
      return movie.genres[0].name;
    }

    return movie.genres.map((g) => g.name).reduce((acc, cur) => acc.concat(`, ${cur}`));
  }

  goToDetail(movie: Movie) {

    this.router.navigate([`/${movie.id}`], {
      state: {
        movie: movie
      }
    });
  }

  getBackGroundUrl() {
    return 'https://image.tmdb.org/t/p/original/' + this.focusMovie.backdrop_path;
  }

  onScroll() {

    this.pageCounter++;
    if (this.searchInput.trim() !== '') {
      this.search(true);
    } else {
      this.loadMovies(true);
    }
  }

  updateUrl($event, movie: Movie) {
    movie.poster_path = 'https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwjqzoPfjN7kAhUSDrkGHVJIBssQjRx6BAgBEAQ&url=http%3A%2F%2Fleeford.in%2Fimage-not-found%2F&psig=AOvVaw1AzYMjprM7TtyfhMC-dAqU&ust=1569024192010407';
  }

  async search(hasScrolled?: boolean) {

    this.waitingRequest = true;
    this.pageCounter = 1;

    if (this.searchInput.trim() === '') {
      this.loadMovies();
    } else {
      const movies = await this.movieService.findByName(this.searchInput, this.pageCounter, 'en-US');

      this.checkSearchIsFromScroll(hasScrolled, movies);
      this.checkResultength(movies);

      this.waitingRequest = false;
    }
  }

  async loadMovies(hasScrolled?: boolean) {
    this.waitingRequest = true;

    const movies = await this.movieService.findAll(this.pageCounter, 'en-US');

    this.checkSearchIsFromScroll(hasScrolled, movies);
    this.checkResultength(movies);

    this.waitingRequest = false;
  }

  private checkSearchIsFromScroll(hasScrolled: boolean, movies: MovieList) {
    if (hasScrolled) {
      this.movieList.results = this.movieList.results.concat(movies.results);
    } else {
      this.movieList = movies;
    }
  }

  private checkResultength(movies: MovieList) {
    if (movies.results.length === 0) {
      this.scrollDistance = this.scrollThrottle = 999;
    } else {
      this.focusMovie = movies.results[0];
    }
  }
}
