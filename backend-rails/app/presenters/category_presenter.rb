class CategoryPresenter < BasePresenter
  attr_accessor :category

  def initialize(category)
    @category = category

    super()
  end

  def payload
    {
      id: category.id,
      name: category.name
    }
  end
end
