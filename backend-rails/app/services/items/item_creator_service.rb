module Items
  class ItemCreatorService < BaseService
    attr_reader :create_item_params

    def initialize(user:, create_item_params:)
      @user = user
      @create_item_params = create_item_params.merge(user:)
    end

    def call
      item = Item.new(create_item_params)

      authorize!(ItemPolicy, :create?, item)

      item.save!
      item
    end
  end
end
