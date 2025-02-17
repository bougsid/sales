package app.payment;

import app.bill.Bill;
import javafx.beans.property.*;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Payment generated by hbm2java
 */
@Entity
@Table(name = "payment", catalog = "sm"
)
@Inheritance(strategy = InheritanceType.JOINED)

public class Payment implements java.io.Serializable {

    private LongProperty idPayment;
    private FloatProperty amount;
    private BooleanProperty avance;
    private ObjectProperty<PaymentState> paymentState;
    private Set<Bill> bills = new HashSet<Bill>(0);

    public Payment() {
        this.idPayment = new SimpleLongProperty();
        this.amount = new SimpleFloatProperty();
        this.avance = new SimpleBooleanProperty();
        this.paymentState = new SimpleObjectProperty<>();
    }

    public LongProperty idPaymentProperty() {
        return idPayment;
    }

    public FloatProperty amountProperty() {
        return amount;
    }

    public ObjectProperty<PaymentState> paymentStateProperty() {
        return paymentState;
    }

    public StringProperty avanceProperty() {
        String str = "non";
        if (avance.get()) str = "oui";
        return new SimpleStringProperty(str);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id_payment", unique = true, nullable = false)
    public Long getIdPayment() {
        return this.idPayment.get();
    }

    public void setIdPayment(Long idPayment) {
        this.idPayment.set(idPayment);
    }

    @Column(name = "amount", precision = 12, scale = 0)
    public Float getAmount() {
        return this.amount.get();
    }

    public void setAmount(Float amount) {
        this.amount.set(amount);
    }

    @Column(name = "avance")
    public Boolean getAvance() {
        return this.avance.get();
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "state")
    public PaymentState getPaymentState() {
        return this.paymentState.get();
    }

    public void setPaymentState(PaymentState paymentState) {
        this.paymentState.set(paymentState);
    }

    public void setAvance(Boolean avance) {
        this.avance.set(avance);
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "bill_payment", catalog = "sm", joinColumns = {
            @JoinColumn(name = "id_payment", nullable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "id_bill", nullable = false, updatable = false)})
    public Set<Bill> getBills() {
        return this.bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }

    public void addBill(Bill bill) {
        this.bills.add(bill);
    }

}
