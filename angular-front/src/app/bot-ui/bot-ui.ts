import {Component, inject} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClient, HttpDownloadProgressEvent, HttpEventType} from '@angular/common/http';
import {Observable, pipe} from 'rxjs';
import {AsyncPipe} from '@angular/common';
import {MarkdownComponent} from 'ngx-markdown';
import {LoadingService} from '../services/loading';
import {map} from 'rxjs/operators';

@Component({
  imports: [
    FormsModule,
    AsyncPipe,
    MarkdownComponent,
  ],
  selector: 'app-bot-ui',
  styleUrl: './bot-ui.css',
  templateUrl: './bot-ui.html',
})
export class BotUi {
  query: any;
  http = inject(HttpClient);
  response$! : Observable<any>;
  public loadingService = inject(LoadingService)

  protected askAgent() {
    this.response$ = this.http.get("http://localhost:9999/EBANK-BOT/chat?query=" + this.query, {responseType: 'text'}); // on doit specifier responseType de type 'text' pour qu'on puisse avoir le type de response
  }

  protected askAgentStream() {
    this.response$ = this.http
      .get("http://localhost:9999/EBANK-BOT/chatStream?query=" + this.query, {responseType: 'text', observe: "events", reportProgress: true}) // on doit specifier responseType de type 'text' pour qu'on puisse avoir le type de response
      .pipe(map(event => {
        switch (event.type) {
          case HttpEventType.Sent:
            return {'type': 'text'}
          case HttpEventType.DownloadProgress:
            return {type: "Response", content: (event as HttpDownloadProgressEvent).partialText}
          case HttpEventType.Response:
            return {type: 'Response', content: event.body}
          default:
            return {type: 'other', data: event}
        }
      }))
  }
}
