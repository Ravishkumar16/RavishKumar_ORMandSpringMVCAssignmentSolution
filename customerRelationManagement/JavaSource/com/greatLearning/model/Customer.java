package com.greatLearning.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")

public class Customer {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int customerid;

		@Column(name="first_name")
		private String firstName;
		
		@Column(name="last_name")
		private String lastName;
		
		@Column(name="email")
		private String email;

		public Customer() {
			super();
		}

		public Customer(String firstName, String lastName, String email) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
		}

		public int getCustomerid() {
			return customerid;
		}

		public void setCustomerid(int customerid) {
			this.customerid = customerid;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Override
		public String toString() {
			return "Customer [customerid=" + customerid + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
		}
			
}
