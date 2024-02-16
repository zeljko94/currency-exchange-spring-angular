import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../environment";

@Injectable({
  providedIn: "root",
})
export class BaseRestApiService {
  protected apiRoute = "";
  constructor(protected http: HttpClient, apiRoute: string) {
    this.apiRoute = environment.backendUrl + apiRoute;
  }

  public getById(id: any): Observable<Object> {
    return this.http.get(this.apiRoute + "/getById/" + id);
  }

  public getAll(): Observable<Object> {
    return this.http.get(this.apiRoute + "/getAll");
  }

  public insert(data: any): Observable<Object> {
    return this.http.post(this.apiRoute + "/insert", data);
  }

  public delete(id: any): Observable<Object> {
    return this.http.delete(this.apiRoute + "/delete/" + id);
  }

  public update(data: any, id: any): Observable<Object> {
    return this.http.put(this.apiRoute + "/update/" + id, data);
  }
}
