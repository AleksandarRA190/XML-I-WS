import { Component, OnInit } from '@angular/core';
import { CommentDTO } from 'app/dto/CommentDTO';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { ReservationDTO } from 'app/dto/ReservationDTO';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css']
})
export class PostCommentComponent implements OnInit {

  request : CommentDTO = new CommentDTO();
  id : number;
  reservation : ReservationDTO;

  constructor(private http: HttpClient,private router : Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => { //uzimanje parametara iz url-a
      this.id = + params['id'];
    });
    console.log(this.id);
    this.request.approvedComment = false;
    this.request.contentOfComment = "";

    this.http.get<ReservationDTO>('http://localhost:9007/reservation/'+this.id).subscribe((data) => {
      this.reservation = data;
      console.log(data);
      if(this.reservation.commentDTO != null) {
        this.request.contentOfComment = this.reservation.commentDTO.contentOfComment;
      }
    });

  }




  onSubmit() {
    this.http.post('http://localhost:9007/reservation/addEditComment/'+this.id, this.request).subscribe((data) => {
        if(data == true) {
          alert('Comment sent to approval!');
        } else {
          alert('Something went wrong!');
        }
    });
    this.request = new CommentDTO();
    this.router.navigate(['reservations']);
    
  }

}
