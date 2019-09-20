import { Genre } from './genre';
export class Movie {
  id: number;
  vote_count: number;
  video: boolean;
  adult: boolean;
  original_title: string;
  original_language: string;
  title: string;
  backdrop_path: string;
  originalImageURL: string;
  poster_path: string;
  overview: string;
  release_date: string;
  popularity: number;
  vote_average: string;
  genre_ids: number[];
  genres: Genre[];
}
