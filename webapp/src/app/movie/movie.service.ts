import { MovieList } from 'src/app/_shared/model/movie-list';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { HttpParams, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private url = `${environment.apiUrl}/movie`;

  constructor(private http: HttpClient) { }

  findAll(pageNumber: number, language: string): Promise<MovieList> {
    const params = new HttpParams()
      .set('pageNumber', pageNumber.toString())
      .set('language', language);

    return this.http.get<MovieList>(`${this.url}/`, { params }).toPromise();
  }

  findByName(name: string, pageNumber: number, language: string): Promise<MovieList> {
    const params = new HttpParams()
      .set('query', name)
      .set('pageNumber', pageNumber.toString())
      .set('language', language);

    return this.http.get<MovieList>(`${this.url}/search`, { params }).toPromise();
  }
}
