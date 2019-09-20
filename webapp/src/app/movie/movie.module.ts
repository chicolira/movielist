import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { MovieRoutingModule } from './movie-routing.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MovieListComponent } from './movie-list/movie-list.component';
import { MzCardModule } from 'ngx-materialize';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MovieDetailComponent } from './movie-detail/movie-detail.component';

@NgModule({
  declarations: [MovieListComponent, MovieDetailComponent],
  imports: [
    CommonModule,
    MovieRoutingModule,
    MzCardModule,
    HttpClientModule,
    FormsModule,
    InfiniteScrollModule
  ]
})
export class MovieModule { }
