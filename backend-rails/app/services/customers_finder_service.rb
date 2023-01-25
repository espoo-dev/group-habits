class CustomersFinderService < BaseService
  attr_reader :user, :name, :customer_type

  def initialize(user:, index_params:)
    @user = user
    @name = index_params[:name]
    @customer_type = index_params[:customer_type]
  end

  def call
    customers = CustomerPolicy::Scope.new(user, Item).resolve
                                     .by_name_like(name)
                                     .by_customer_type(customer_type)

    authorize!(CustomerPolicy, :index?, customers)

    customers
  end
end
