package Model;

import java.io.Serializable;

public class Task implements Serializable {

    /**
     * The id of the task
     */
    private String id;
    /**
     * A bried description of the task
     */
    private String briefDescription;
    /**
     * Time duration of the task
     */
    private Integer timeDuration;
    /**
     * Cost per hour of the task
     */
    private Float costPerHour;
    /**
     * Task Category
     */
    private String taskCategory;
    /**
     * The payment transaction associated with the task
     */
    private PaymentTransaction payment;

    /**
     * Creates an instance of Task with the id, briefDescription, timeDuration,
     * end date, costPerHour and the taskCategory
     *
     * @param id
     * @param briefDescription
     * @param timeDuration
     * @param costPerHour
     * @param taskCategory
     */
    public Task(String id, String briefDescription, Integer timeDuration, Float costPerHour, String taskCategory) {

        if ((id == null) || (briefDescription == null) || (timeDuration <= 0) || (costPerHour <= 0) || (taskCategory == null)
                || (id.isEmpty()) || (briefDescription.isEmpty()) || (taskCategory.isEmpty())) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        }

        this.id = id;
        this.briefDescription = briefDescription;
        this.timeDuration = timeDuration;
        this.costPerHour = costPerHour;
        this.taskCategory = taskCategory;
    }

    /**
     * Returns a textual description of the task with the format: id, Brief
     * Description, Time Duration and cost per hour
     *
     * @return the textual description
     */
    @Override
    public String toString() {
        return String.format("ID: %s;Brief Description: %s; Time Duration: %s; Cost Per Hour: %.02f;"
                + " Task Category: %s", getId(), getBriefDescription(), getTimeDuration().toString(), getCostPerHour(), getTaskCategory());
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the briefDescription
     */
    public String getBriefDescription() {
        return briefDescription;
    }

    /**
     * @return the timeDuration
     */
    public Integer getTimeDuration() {
        return timeDuration;
    }

    /**
     * @return the costPerHour
     */
    public Float getCostPerHour() {
        return costPerHour;
    }

    /**
     * @return the taskCategory
     */
    public String getTaskCategory() {
        return taskCategory;
    }

    /**
     * @return the payment
     */
    public PaymentTransaction getPaymentTransaction() {
        return payment;
    }

    /**
     * Changes the payment transaction
     *
     * @param payment
     */
    public void setPaymentTransaction(PaymentTransaction payment) {
        this.payment = payment;
    }

    /**
     * Compares if the task passed by parameter is the same as the current
     *
     * @param outroObjeto
     * @return if it is the same
     */
    @Override
    public boolean equals(Object outroObjeto) {
        if (this == outroObjeto) {
            return true;
        }
        if (outroObjeto == null || this.getClass() != outroObjeto.getClass()) {
            return false;
        }
        Task outroMana = (Task) outroObjeto;

        return id.equalsIgnoreCase(outroMana.id) && briefDescription.equalsIgnoreCase(outroMana.briefDescription)
                && timeDuration == outroMana.timeDuration && costPerHour == outroMana.costPerHour && taskCategory.equalsIgnoreCase(outroMana.taskCategory)
                && payment.equals(outroMana.payment);
    }
}
